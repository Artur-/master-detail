import * as Endpoint from "../../generated/PersonEndpoint";
import Entity from "../../generated/com/example/application/data/entity/Person";
import { PolymerElement } from '@polymer/polymer/polymer-element.js';
import { html, LitElement } from '@polymer/polymer/lib/utils/html-tag.js';
import '@vaadin/vaadin-split-layout/vaadin-split-layout.js';
import '@vaadin/vaadin-grid/vaadin-grid.js';
import '@vaadin/vaadin-grid/vaadin-grid-column.js';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/vaadin-form-item.js';
import '@vaadin/vaadin-text-field/vaadin-text-field.js';
import '@vaadin/vaadin-text-field/vaadin-password-field.js';
import '@vaadin/vaadin-button/vaadin-button.js';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout.js';

class MasterdetailviewdesignerView extends PolymerElement {
  static get template() {
    return html`
      <vaadin-split-layout class="full-size">
        <div class="grid-wrapper">
          <vaadin-grid id="grid" class="full-size" theme="no-border"></vaadin-grid>
        </div>
        <div id="editor-layout">
          <vaadin-form-layout>
            <vaadin-form-layout><vaadin-text-field
          label="Profile Picture"
          class="full-width"
          id="profilePicture"
        ></vaadin-text-field><vaadin-text-field
          label="First Name"
          class="full-width"
          id="firstName"
        ></vaadin-text-field><vaadin-text-field
          label="Last Name"
          class="full-width"
          id="lastName"
        ></vaadin-text-field><vaadin-text-field
          label="Email"
          class="full-width"
          id="email"
        ></vaadin-text-field><vaadin-text-field
          label="Occupation"
          class="full-width"
          id="occupation"
        ></vaadin-text-field></vaadin-form-layout>
          </vaadin-form-layout>
          <vaadin-horizontal-layout id="button-layout" theme="spacing">
            <vaadin-button id="cancel" theme="tertiary">
              Cancel
            </vaadin-button>
            <vaadin-button id="save" theme="primary">
              Save
            </vaadin-button>
          </vaadin-horizontal-layout>
        </div>
      </vaadin-split-layout>
    `;
  }

  static get is() {
    return 'masterdetailviewdesigner-view';
  }

  _attachDom(dom) {
    // Do not use shadow DOM for easier styling
    this.appendChild(dom);
  }
}

customElements.define(MasterdetailviewdesignerView.is, MasterdetailviewdesignerView);
