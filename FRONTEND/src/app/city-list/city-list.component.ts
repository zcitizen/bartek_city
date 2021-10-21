import { Component, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CityDto } from '../_models/city-dto';
import { CityService } from '../_services/city-service';

@Component({
  selector: 'app-city-list',
  templateUrl: './city-list.component.html',
  styleUrls: ['./city-list.component.css']
})
export class CityListComponent implements OnInit {

  currentPage: number = 1;
  pageSize: number = 10;
  maxPageNumber: number = 1;


  cityList: CityDto[];

  constructor(private cityService: CityService) { 
    this.cityList = [];
  }

  ngOnInit(): void {
    this.getPagesForSize(this.pageSize);
    this.getCities();
  }

  getCities(){
    this.cityService.getCityList(this.currentPage, this.pageSize)
      .subscribe((data: CityDto[]) => this.cityList = data);
  }

  getPagesForSize(size: number) {
    if (size == undefined){
      size = 10;
    }
    this.cityService.getPageNumberCount(size).subscribe(response => {
      this.maxPageNumber = response.pagesCount;
      this.currentPage = 1;
    })
  }

  saveChangesCallback = (id: number, name: string, image: string): void => {
    let dto: CityDto = {
        id: id,
        name: name,
        photo: image
      };
    this.cityService.updateCity(dto).subscribe(response => {
      this.cityList = [];
      this.getCities()

    });
  }

  nextPage() {
    this.currentPage += 1;
    this.getCities();
    window.scroll(0,0);

  }

  previousPage(){
    this.currentPage -= 1;
    this.getCities();
    window.scroll(0,0);
  }

}
