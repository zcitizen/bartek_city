import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './_models/user';
import { AuthenticationService } from './_services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isLogged: boolean;


  constructor(
      private router: Router,
      private authenticationService: AuthenticationService
  ) {
      this.isLogged = false;
      this.authenticationService.authData.subscribe(x=> {
        if (x){
          this.isLogged = true;
        }
      })
  }

  logout() {
      this.isLogged = false;
      this.authenticationService.logout();
  }



}
