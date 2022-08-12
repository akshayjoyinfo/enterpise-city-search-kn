import { Component, ViewChild, TemplateRef } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Constants } from '../config/constatnts';
import { City } from './core/models/city.model';
import { CityApiService } from './core/services/city-api.service';
import { ApiPaginatedModel } from './core/models/api-model';
import { Subject, debounce, interval, map, observable, Observable } from 'rxjs';
import { debounceTime } from 'rxjs/operators';


import { Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { AuthService } from '@auth0/auth0-angular';

import { MatDialog } from '@angular/material/dialog';
import { EditCityDialogComponent } from './components/edit-city-dialog/edit-city.dialog.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {

}
