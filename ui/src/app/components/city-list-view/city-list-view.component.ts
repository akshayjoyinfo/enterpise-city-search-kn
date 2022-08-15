import { Component, ViewChild, TemplateRef } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Constants } from '../../../config/constatnts';
import { City } from '../../core/models/city.model';
import { CityApiService } from '../../core/services/city-api.service';
import { ApiPaginatedModel } from '../../core/models/api-model';
import { Subject, debounce, interval, map, observable, Observable } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';

import { Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { AuthService } from '@auth0/auth0-angular';

import { MatDialog } from '@angular/material/dialog';
import { EditCityDialogComponent } from '../edit-city-dialog/edit-city.dialog.component'


@Component({
  selector: 'city-list-view',
  templateUrl: './city-list-view.component.html',
  styleUrls: ['./city-list-view.component.scss'],
})
export class CityListViewComponent {
  
  isCollapsed = true;

  cityFiltersubject = new Subject();
  CITY_DATA: City[] = [];
  isLoading = false;
  totalRows = 0;
  pageSize = 10;
  currentPage = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  gridColumns = 3;

  displayedColumns: string[] = ['photo', 'name', 'actions'];
  dataSource: MatTableDataSource<City> = new MatTableDataSource();

  myFooList = ['Some Item', 'Item Second', 'Other In Row', 'What to write', 'Blah To Do']

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;


  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;

  constructor(
    public restApi: CityApiService,
    public auth: AuthService,
    @Inject(DOCUMENT) private doc: Document,
    public dialog: MatDialog,
    private _snackBar: MatSnackBar
  ) {
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
    //Load initial data
    this.loadData('');
    this.cityFiltersubject.pipe(debounceTime(300)).subscribe((searchtext) => {
      this.loadData(searchtext as string);
    });
  }

  loadData(searchText: string) {
    // why settimeout to test loading

    this.restApi
      .getCities(searchText, this.currentPage + 1, this.pageSize)
      .subscribe((data: ApiPaginatedModel<City[]>) => {
        this.dataSource.data = data.results;
        setTimeout(() => {
          this.paginator.pageIndex = this.currentPage;
          this.paginator.length = data.refs.totalCount;
        });
        this.isLoading = false;
      });
  }
  filterCities(evt: any) {
    const searchText = evt.target.value;
    // emits the `searchText` into the stream. This will cause the operators in its pipe function (defined in the ngOnInit method) to be run. `debounce` runs and then `map`. If the time interval of 1 sec in debounce hasn't elapsed, map will not be called, thereby saving the server from being called.
    this.cityFiltersubject.next(searchText);
  }

  pageChanged(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadData('');
  }

  loginWithRedirect() {
    this.auth.loginWithRedirect();
  }

  logout() {
    this.auth.logout({ returnTo: this.doc.location.origin });
  }

  openEditDialog(elem:any) {
    const myCompDialog = this.dialog.open(EditCityDialogComponent, {
      data: elem,
      panelClass: 'fullscreen-dialog',
      height: '55vh',
      width: '60%'
    });
    myCompDialog.afterClosed().subscribe((res) => {
      // Data back from dialog
      if(res.status ==='200 OK')
        this._snackBar.open('Updated City', 'SAVED',{
          duration: 3000,
        });
    });
  }
}
