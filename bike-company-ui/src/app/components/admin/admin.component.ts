import { Component, OnInit } from '@angular/core';
import { BikeCompanyService } from '../../services/bike-company.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  public bikes;

  constructor(private bikeCompanyService: BikeCompanyService) { }

  ngOnInit(): void {
    this.getBikes();
  }

  getBikes() {
    this.bikeCompanyService.getBikes().subscribe(
      data => { this.bikes = data},
      err => console.error(err),
      () => console.log('bikes loaded')
    );
  }

}
