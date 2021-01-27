import {Component, OnInit, Output} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {EventEmitter} from '@angular/core';

@Component({
  selector: 'app-toolbar-user',
  templateUrl: './toolbar-user.component.html',
  styleUrls: ['./toolbar-user.component.scss']
})
export class ToolbarUserComponent implements OnInit {
    @Output() refresh = new EventEmitter<void>();

    refreshParent(): void {
        this.refresh.emit();
    }

    constructor(
      public authService: AuthenticationService
    ) { }

    ngOnInit(): void {
    }

    logout(): void {
        this.authService.logout();
        this.refreshParent();
    }
}
