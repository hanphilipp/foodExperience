import { Component, OnInit } from '@angular/core';
import { ApiDataServiceService } from'./api-data-service.service';
import { Observable } from 'rxjs';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  weight:number = 80;
  ingredient1:string = "";
  ingredient2:string = "";
  ingredient3:string ="";
  ingredient4:string ="";
  ingredient5:string ="";
  title = 'WebServices';
  recipes : Observable<any>;
  recipesv2 : any;
  activatedRecipe: any = {};
  enteredFormular : boolean = false;
  constructor (private apiDataService: ApiDataServiceService){

  }
  
  ngOnInit(): void {
    
  }

  public test(item:any){
    console.log(item);
    this.recipesv2 = item;
  }

  public saveRecipe(activatedRecipe:any){
    if(this.activatedRecipe==activatedRecipe){
      this.activatedRecipe = {};
    }
    else{
    this.activatedRecipe=activatedRecipe;
    }
    console.log(activatedRecipe);
  }

  public saveInput(){
    this.apiDataService.getRecipes(this.ingredient1+','+this.ingredient2+','+this.ingredient3+','+this.ingredient4+','+this.ingredient5, ''+this.weight+'')
    .subscribe(data => this.test(data));
    console.log("saving");
    this.enteredFormular = true;
  }

  public indexCheck(index: number){
    return (index<=8);
  }

  recipesExistingCheck(){
    if(this.recipesv2.recipes.length<=0){
      this.recipesv2 = {};
      return false
    }
    return true;
  }
  formularCheck(){
    if(!this.enteredFormular){
      this.recipesv2 = {};
      return false
    }
    return true;
  }


}
