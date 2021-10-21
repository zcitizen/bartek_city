import { Component, OnInit } from '@angular/core';
import { startWith } from 'rxjs/operators';
import { CityDto } from '../_models/city-dto';
import { CityService } from '../_services/city-service';

@Component({
  selector: 'app-city-search',
  templateUrl: './city-search.component.html',
  styleUrls: ['./city-search.component.css']
})
export class CitySearchComponent implements OnInit {

  city: CityDto;
  searchValue: string = '';
  found: boolean = false;
  searchClicked: boolean = false;


  constructor(private cityService: CityService) { }

  ngOnInit(): void {
  }


  onSearchClick() {
    if(!this.searchValue){
      return;
    }
    this.search(this.searchValue);
    this.searchClicked = true;
    

  }
  saveChangesCallback = (id: number, name: string, image: string): void => {
    let dto: CityDto = {
        id: id,
        name: name,
        photo: image
      };
    this.cityService.updateCity(dto).subscribe(response => {
    this.search(dto.name);
    });
  }

  private search(startsWith: string){
    this.cityService.getCityByNameStartingWith(startsWith).subscribe(result => {
      if (result) {
        this.city = result;
        this.found = true;
      } else {
        this.found = false;
      }
    });
  }

}
