import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment'

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    private authSubject: BehaviorSubject<String>;
    public authData: Observable<String>;


    constructor(
        private router: Router,
        private http: HttpClient
    ) {
        this.authSubject =  new BehaviorSubject<String>(JSON.parse(localStorage.getItem('authReq')));
        this.authData = this.authSubject.asObservable();
    }

    public get authDataValue(): String {
        return this.authSubject.value;
    }

    login(username: string, password: string) {
        const authReq = 'Basic ' + btoa(username + ':' + password);
        const headers = new HttpHeaders({
            authorization : `${authReq}`
        });
       
        return this.http.get<any>(`${environment.apiUrl}/login`, {headers: headers}).pipe(map(data => {
                localStorage.setItem('authReq', JSON.stringify(authReq));
            })
        );
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('authReq');
        this.authSubject.next(null);
        this.router.navigate(['/login']);
    }
    
}