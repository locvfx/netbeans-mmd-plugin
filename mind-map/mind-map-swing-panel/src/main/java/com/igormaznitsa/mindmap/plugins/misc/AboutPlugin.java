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
package com.igormaznitsa.mindmap.plugins.misc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import com.igormaznitsa.meta.annotation.MustNotContainNull;
import com.igormaznitsa.mindmap.model.Topic;
import com.igormaznitsa.mindmap.plugins.api.AbstractPopupMenuItem;
import com.igormaznitsa.mindmap.plugins.PopUpSection;
import com.igormaznitsa.mindmap.swing.panel.DialogProvider;
import com.igormaznitsa.mindmap.swing.panel.MindMapPanel;
import com.igormaznitsa.mindmap.swing.panel.Texts;
import com.igormaznitsa.mindmap.swing.services.IconID;
import com.igormaznitsa.mindmap.swing.services.ImageIconServiceProvider;
import com.igormaznitsa.mindmap.plugins.api.CustomJob;

public class AboutPlugin extends AbstractPopupMenuItem {
  private static final Icon ICO = ImageIconServiceProvider.findInstance().getIconForId(IconID.POPUP_ABOUT);

  @Override
  @Nullable
  public JMenuItem makeMenuItem(@Nonnull final MindMapPanel panel, @Nonnull final DialogProvider dialogProvider, @Nullable final Topic topic, @Nonnull @MustNotContainNull final Topic[] selectedTopics, @Nullable final CustomJob customProcessor) {
    final JMenuItem result = UI_COMPO_FACTORY.makeMenuItem(Texts.getString("MMDGraphEditor.makePopUp.miAbout"), ICO);
      final AboutPlugin theInstance = this;  
      result.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(@Nonnull ActionEvent e) {
            if (customProcessor!=null){
              customProcessor.doJob(theInstance, panel, dialogProvider, topic, selectedTopics);
            }
          }
        });
    return result;
  }

  @Override
  @Nonnull
  public PopUpSection getSection() {
    return PopUpSection.MISC;
  }

  @Override
  public boolean needsTopicUnderMouse() {
    return false;
  }

  @Override
  public boolean needsSelectedTopics() {
    return false;
  }
  
  @Override
  public int getOrder() {
    return Integer.MAX_VALUE;
  }
  
}
