import {  CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule} from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatPaginatorModule } from '@angular/material/paginator';
import  { MatProgressBarModule} from '@angular/material/progress-bar';
import { CityApiService } from './core/services/city-api.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthModule } from '@auth0/auth0-angular';
import { AuthHttpInterceptor } from '@auth0/auth0-angular';
import { MatDialogModule } from '@angular/material/dialog';
import { EditCityDialogComponent } from './components/edit-city-dialog/edit-city.dialog.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CityListViewComponent } from './components/city-list-view/city-list-view.component';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';


@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MatCardModule,
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
    HttpClientModule,
    AuthModule.forRoot({
       domain: 'dev-krypton-city-search.us.auth0.com',
       clientId: 'QqpDKaGbeJLgWn7P7DTSQk6buF2W290A',
       audience: "https://city-search-api.example.com",
       httpInterceptor: {
        allowedList: [`http://localhost:9050/api/v1/cities/*`,`https://city-search-api-springboot.herokuapp.com/api/v1/cities/*`],
       }
    })
  ],
  providers: [ CityApiService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthHttpInterceptor, multi: true }],
  bootstrap: [AppComponent],
  declarations:[AppComponent, EditCityDialogComponent, CityListViewComponent],
  schemas: [
    NO_ERRORS_SCHEMA,
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule { }
