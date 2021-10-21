import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input("id") public id: number;
  @Input("name") public name: string;
  @Input("image") public image: string;
  @Input() public callbackFunction: (id: number, name: string, image: string) => void;

  public editMode: boolean;

  nameCopy: string;
  imageCopy: string;

  constructor() {
    this.editMode = false;
   }

  ngOnInit(): void {
    this.nameCopy = this.name;
    this.imageCopy = this.image;
  }

  public edit(){
    this.editMode = true;
  }

  public save(){
    this.editMode = false;
    this.callbackFunction(this.id, this.nameCopy, this.imageCopy);
  }

  public cancel() {
    this.nameCopy = this.name;
    this.imageCopy = this.image;
    this.editMode = false;
  }

}
