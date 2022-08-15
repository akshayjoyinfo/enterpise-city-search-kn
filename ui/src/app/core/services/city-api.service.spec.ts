import { CommonModule } from "@angular/common";
import { HttpClient, HttpClientModule, HttpErrorResponse } from "@angular/common/http";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { defer, Observable, of, throwError } from "rxjs";
import { Cities } from "test/testData/cities";
import { CityApiService } from "./city-api.service";

export function asyncError<T>(errorObject: any) {
    return defer(() => Promise.reject(errorObject));
  }
  
describe('CityApiService', () => {
    let httpclient: HttpClient;
    let service: CityApiService;
    beforeEach(async () => {
        await TestBed.configureTestingModule({
          imports: [
            CommonModule,
            HttpClientModule,
          ],
          providers: [HttpClient]
        })    
        httpclient = TestBed.get(HttpClient);
        service = new CityApiService(httpclient);
      });

      it('should return an expected list of cities', (done: DoneFn) => {
        spyOn(httpclient, 'get').and.returnValue(of(Cities.apiResponse));
    
        service.getCities().subscribe(
          cities => {
            expect(cities.results).toHaveSize(10);
            done();
          },
          done.fail
        );
      });

      it('should throw error invalid api response', () => {
        const errorResponse = new HttpErrorResponse({
            error: '500 error',
            status: 500,
            statusText: 'Internal Server Error'
          });
       
        spyOn(httpclient, 'get').and.returnValue(throwError(errorResponse));
    
        service.getCities().subscribe(
            data => {},
            (error: HttpErrorResponse) => {
                console.log(error);
                expect(JSON.stringify(error)).toContain('500');             
            });
      });

      it('should update city', (done: DoneFn) => {
        spyOn(httpclient, 'put').and.returnValue(of(Cities.basicCitySingleResponse));
    
        service.updateCity({
            _id: 1,
            name: 'l',
            photo: 'p'
        }).subscribe(
          city => {
            expect(JSON.stringify(city)).toContain('200 OK');
            done();
          },
          done.fail
        );
      });

      it('should throw error while updating Status', () => {
        const errorResponse = new HttpErrorResponse({
            error: '403 error',
            status: 403,
            statusText: 'Forbidden Access'
          });
       
        spyOn(httpclient, 'put').and.returnValue(throwError(errorResponse));
    
        service.updateCity({
            _id: 1,
            name: 'l',
            photo: 'p'
        }).subscribe(
            data => {},
            (error: HttpErrorResponse) => {
                console.log(error);
                expect(JSON.stringify(error)).toContain('403');             
            });
      });
    });
