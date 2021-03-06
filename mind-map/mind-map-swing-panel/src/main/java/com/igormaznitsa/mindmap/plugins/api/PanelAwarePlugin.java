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
package com.igormaznitsa.mindmap.plugins.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.igormaznitsa.mindmap.model.MindMap;
import com.igormaznitsa.mindmap.swing.panel.MindMapPanel;

/**
 * Interface for plug-in to be aware for operations over panels.
 * 
 * @since 1.2
 */
public interface PanelAwarePlugin extends MindMapPlugin {
  void onPanelCreate(@Nonnull MindMapPanel panel);
  void onPanelModelChange(@Nonnull MindMapPanel panel, @Nullable MindMap oldModel, @Nonnull MindMap newModel);
  void onPanelDispose(@Nonnull MindMapPanel panel);
}
