import {Component, OnInit} from '@angular/core';
import {MyProfileDto} from "../_models/MyProfileDto";
import {UserService} from "../_services/UserService";
import {ProductService} from "../_services/ProductService";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  public profile: MyProfileDto;
  public orders = [
    {id: 1, number: 2, status: "COMPLETED", date: "2023-05-13"},
    {id: 1, number: 2, status: "CREATED", date: "2023-05-13"},
    {id: 1, number: 2, status: "COMPLETED", date: "2023-05-13"},
  ];
  displayedColumns: any[] = [
    'number', 'status', 'date'
  ];

  constructor(private userService: UserService, public productService: ProductService) {
  }

  public showOrderDetails(id: number) {
    console.log("Order details with id: ", id);
  }

  ngOnInit(): void {
    this.userService.getMyProfile().subscribe(
      (profile: MyProfileDto) => {
        this.profile = profile;
        localStorage.setItem("user", JSON.stringify(profile));
      });
  }
}
