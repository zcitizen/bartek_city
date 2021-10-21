import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment'
import { AuthenticationService } from '../_services/authentication.service';

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // add header with basic auth credentials if user is logged in and request is to the api url
        const userBasicAuthData = this.authenticationService.authDataValue;
        const isApiUrl = request.url.startsWith(environment.apiUrl);
        if (isApiUrl && userBasicAuthData) {
            request = request.clone({
                setHeaders: { 
                    authorization: `${userBasicAuthData}`
                }
            });
        }

        return next.handle(request);
    }
}