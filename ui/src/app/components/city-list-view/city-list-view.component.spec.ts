import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthModule, AuthService } from '@auth0/auth0-angular';
import { CityApiService } from 'src/app/core/services/city-api.service';
import { CityListViewComponent } from './city-list-view.component';
import { environment as env } from '../../../environments/environment';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { Cities } from 'test/testData/cities';
import { of } from 'rxjs';

describe('CityListView component tests', () => {
  let component: CityListViewComponent;
	let service: CityApiService;
  let httpClient: HttpClient;
  let fixture: ComponentFixture<CityListViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CommonModule,
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,MatCardModule,
        MatToolbarModule,
        MatButtonModule,
        FlexLayoutModule,
        MatFormFieldModule,
        MatInputModule,
        MatInputModule,
        MatIconModule,
        MatSelectModule,
        MatTableModule, 
        MatPaginatorModule,
        MatProgressBarModule,
        MatDialogModule,
        MatSnackBarModule,
        AuthModule.forRoot({
          clientId:'',
          domain:'',
          audience:'',

        })
    
      ],
      declarations: [
        CityListViewComponent
      ],
      providers: [CityApiService, AuthService]
    }).compileComponents();

    service = TestBed.get(CityApiService);
    fixture = TestBed.createComponent(CityListViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

  });

  it('should create the CityListView', () => {

    var cities = Cities.basicCityResponse;

    spyOn(service, 'getCities').and.returnValue(of(cities));

		component.ngOnInit();

    expect(component).toBeTruthy();
  });

  it('should have valid cities', () => {

    var cities = Cities.basicCityResponse;

    spyOn(service, 'getCities').and.returnValue(of(cities));

		component.ngOnInit();

    const compiled = fixture.debugElement.nativeElement;

    expect(compiled.querySelector('mat-paginator').getAttribute('aria-label')).toEqual('Select page');
    expect(compiled.querySelector('mat-table')).toBeDefined();

    var tableElement = compiled.querySelector('.mat-table');

    let columns = tableElement.querySelectorAll('thead tr th');
    let columns_actions = tableElement.querySelectorAll('thead tr mat-header-cell');
    let rows = tableElement.querySelectorAll('tbody tr');

    expect(rows.length).toEqual(10);
    expect(columns.length).toEqual(2); //2 columns from api 1 column for action
    expect(columns_actions.length).toEqual(1);

    var paginatorElement = compiled.querySelector('mat-paginator');
    expect(paginatorElement).toBeDefined();
  });



});
