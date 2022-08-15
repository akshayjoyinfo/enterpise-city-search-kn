import { ApiModel, ApiPaginatedModel } from "src/app/core/models/api-model";
import { City } from "src/app/core/models/city.model";

export class Cities{
      public static apiResponse:any = {
        "results": [
          {
            "name": "Delhi",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/IN-DL.svg/439px-IN-DL.svg.png",
            "_id": 3
          },
          {
            "name": "Mumbai",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Mumbai_Skyline_at_Night.jpg/500px-Mumbai_Skyline_at_Night.jpg",
            "_id": 4
          },
          {
            "name": "Manila",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Manila_Cathedral_Facade_at_Sunset.jpg/500px-Manila_Cathedral_Facade_at_Sunset.jpg",
            "_id": 5
          },
          {
            "name": "Shanghai",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Pudong_Shanghai_November_2017_panorama.jpg/500px-Pudong_Shanghai_November_2017_panorama.jpg",
            "_id": 6
          },
          {
            "name": "São Paulo",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f4/S%C3%A3o_Paulo_GTXDrone_Capture_134.255.jpg/500px-S%C3%A3o_Paulo_GTXDrone_Capture_134.255.jpg",
            "_id": 7
          },
          {
            "name": "Seoul",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/%EB%8D%95%EC%88%98%EA%B6%81_2011%EB%85%84_11%EC%9B%94_%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD_%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C_%EB%AA%85%EC%86%8C_%28Seoul_best_attractions%29_%EC%82%AC%EB%B3%B8_-1S6O1452.jpg/500px-%EB%8D%95%EC%88%98%EA%B6%81_2011%EB%85%84_11%EC%9B%94_%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD_%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C_%EB%AA%85%EC%86%8C_%28Seoul_best_attractions%29_%EC%82%AC%EB%B3%B8_-1S6O1452.jpg",
            "_id": 8
          },
          {
            "name": "Mexico City",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Montaje.Ciudad_de_M%C3%A9xico.jpg/250px-Montaje.Ciudad_de_M%C3%A9xico.jpg",
            "_id": 9
          },
          {
            "name": "Guangzhou",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Guangzhou_Twin_Towers.jpg/500px-Guangzhou_Twin_Towers.jpg",
            "_id": 10
          },
          {
            "name": "Beijing",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Tiananmen_Gate.jpg/500px-Tiananmen_Gate.jpg",
            "_id": 11
          },
          {
            "name": "Cairo",
            "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Cairo-Nile-2020%281%29.jpg/500px-Cairo-Nile-2020%281%29.jpg",
            "_id": 12
          }
        ],
        "refs": {
          "page": 1,
          "size": 10,
          "totalCount": 1000,
          "currentPage": "/api/v1?page=1&size=10",
          "nextPage": "/api/v1?page=2&size=10"
        }
      } 

      public static citySingleResponse:any = {
        "status": "200 OK",
        "results": {
          "name": "Tokyo",
          "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg/500px-Skyscrapers_of_Shinjuku_2009_January.jpg",
          "_id": 1
        }
      }
      public static basicCityResponse: ApiPaginatedModel<City[]> = this.apiResponse;

      public static basicCitySingleResponse: ApiModel<City> = this.citySingleResponse;

}
