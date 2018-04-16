import { Component, OnInit, Input } from '@angular/core';
import { Language } from '../language';

@Component({
  selector: 'app-language-item',
  templateUrl: './language-item.component.html',
  styleUrls: ['./language-item.component.css']
})
export class LanguageItemComponent implements OnInit {
  @Input() lang: Language;
  constructor() { }

  ngOnInit() {
  }

}

