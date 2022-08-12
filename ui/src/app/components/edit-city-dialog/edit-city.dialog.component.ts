import { Component, Inject, OnInit, Optional } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiModel, ApiPaginatedModel } from '../../core/models/api-model';
import { City } from '../../core/models/city.model';
import { CityApiService } from '../../core/services/city-api.service';

@Component({
  selector: 'edit-city-dialog',
  templateUrl: './edit-city.dialog.component.html',
  styleUrls: ['./edit-city.dialog.component.scss']
})
export class EditCityDialogComponent implements OnInit {

  isLoading = false;
  constructor(
    public dialogRef: MatDialogRef<EditCityDialogComponent>,
    @Optional() @Inject(MAT_DIALOG_DATA) public editCityData: City,
    public restApi: CityApiService,
    private _snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
  }

  closeDialog() { this.dialogRef.close({ event: 'close', data: '111' }); }

  saveCityDialog(){
    console.log('inside SAVE dialog');
    console.log(this.editCityData);
    
    this.restApi
      .updateCity(this.editCityData)
      .subscribe(data => {
        console.log(data);
        this.isLoading = false;
        this.dialogRef.close(data); 
      },
      error =>{
        console.log(error);
        this._snackBar.open('Updated City - No Permission for EDIT', 'FAILED',{
          duration: 3000,
        });
      });

  }
}