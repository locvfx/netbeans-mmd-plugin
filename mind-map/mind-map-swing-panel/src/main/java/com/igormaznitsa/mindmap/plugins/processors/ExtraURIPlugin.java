/*
 * Copyright 2015-2018 Igor Maznitsa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.igormaznitsa.mindmap.plugins.processors;

import com.igormaznitsa.mindmap.plugins.api.AbstractFocusedTopicPlugin;
import javax.annotation.Nonnull;
import com.igormaznitsa.mindmap.model.Topic;
import com.igormaznitsa.mindmap.swing.panel.DialogProvider;
import com.igormaznitsa.mindmap.swing.panel.MindMapPanel;
import com.igormaznitsa.mindmap.swing.panel.ui.AbstractElement;
import com.igormaznitsa.mindmap.swing.panel.Texts;
import com.igormaznitsa.mindmap.swing.services.IconID;
import com.igormaznitsa.mindmap.swing.services.ImageIconServiceProvider;
import javax.annotation.Nullable;
import com.igormaznitsa.meta.annotation.MustNotContainNull;
import com.igormaznitsa.mindmap.model.Extra;
import com.igormaznitsa.mindmap.plugins.PopUpSection;
import javax.swing.Icon;
import static com.igormaznitsa.meta.common.utils.Assertions.assertNotNull;

public class ExtraURIPlugin extends AbstractFocusedTopicPlugin {

  private static final Icon ICO = ImageIconServiceProvider.findInstance().getIconForId(IconID.POPUP_EXTRAS_URI);

  
  @Override
  public int getOrder() {
    return 3;
  }

  @Override
  @Nullable
  protected Icon getIcon(@Nonnull final MindMapPanel panel, @Nullable final Topic actionTopic, @Nonnull @MustNotContainNull final Topic[] selectedTopics) {
    return ICO;
  }

  @Override
  @Nonnull
  protected String getName(@Nonnull final MindMapPanel panel, @Nullable final Topic actionTopic, @Nonnull @MustNotContainNull final Topic[] selectedTopics){
    if (actionTopic == null) return "...";
    return actionTopic.getExtras().containsKey(Extra.ExtraType.LINK) ? Texts.getString("MMDGraphEditor.makePopUp.miEditURI") : 
        Texts.getString("MMDGraphEditor.makePopUp.miAddURI");
  }

  @Override
  protected void doActionForTopic(@Nonnull final MindMapPanel panel, @Nonnull final DialogProvider dialogProvider, @Nullable final Topic actionTopic, @Nonnull @MustNotContainNull final Topic[] selectedTopics){
    if (actionTopic!=null)
    panel.startEdit(assertNotNull((AbstractElement)actionTopic.getPayload()));
  }

  @Override
  @Nonnull
  public PopUpSection getSection() {
    return PopUpSection.EXTRAS;
  }

}
