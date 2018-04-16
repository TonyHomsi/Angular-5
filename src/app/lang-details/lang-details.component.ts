import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Language } from '../language';
import { LangService } from '../lang.service';
import { UserService } from '../user.service';
import { User } from '../user';


class UserLanguageState {
  constructor(public user: User, public state: boolean) {
  }
  }

@Component({
  selector: 'app-lang-details',
  templateUrl: './lang-details.component.html',
  styleUrls: ['./lang-details.component.css']
})


export class LangDetailsComponent implements OnInit {
  userStates: UserLanguageState[];
  @Input() lang: Language;
  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private langService: LangService,
    private router: Router,
  ) { }
  save() {
    this.langService.updateLang(this.lang)
    .subscribe(res => { this.goBack(); });
    }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.langService.getLang(id).subscribe(data => {
       this.onLangChanged(data); });
       this.userService.getUsers().subscribe(data => {
        this.userStates = data.map(user => new UserLanguageState(user, false));
        });
  }
  onLangChanged(lang: Language) {
    this.lang = lang;
    }
    goBack() {
      this.router.navigateByUrl('/lang');
      }
      updateUserKnowledge() {
        
        const userIds = this.userStates.filter(state => state.state).map(state => state.user.id);
        this.langService.updateLanguageMap(this.lang.id, userIds);
        console.log(userIds);
        }

}
