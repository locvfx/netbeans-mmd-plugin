/*
 * Copyright 2016 Igor Maznitsa.
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
package com.igormaznitsa.sciareto.ui.platform;

import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.apple.eawt.Application;
import com.apple.eawt.ApplicationEvent;
import com.apple.eawt.ApplicationListener;
import com.igormaznitsa.meta.annotation.MayContainNull;
import com.igormaznitsa.meta.common.utils.Assertions;

class PlatformMacOSX implements Platform, ApplicationListener {

  private final Application application;

  private final Map<PlatformMenuEvent, PlatformMenuAction> actions = Collections.synchronizedMap(new EnumMap<PlatformMenuEvent, PlatformMenuAction>(PlatformMenuEvent.class));

  public PlatformMacOSX() {
    this.application = Application.getApplication();
  }

  @Override
  public boolean registerPlatformMenuEvent(@Nonnull final PlatformMenuEvent event, @Nonnull final PlatformMenuAction action) {
    this.actions.put(event, Assertions.assertNotNull(action));

    switch (event) {
      case ABOUT: {
        this.application.setEnabledAboutMenu(true);
      }
      break;
      case PREFERENCES: {
        this.application.setEnabledPreferencesMenu(true);
      }
      break;
    }

    return true;
  }

  @Override
  public void init() {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    System.setProperty("com.apple.mrj.application.apple.menu.about.name", "SciaReto");
  }

  @Override
  @Nonnull
  public String getDefaultLFClassName() {
    return UIManager.getSystemLookAndFeelClassName();
  }

  @Override
  public void dispose() {
  }

  private boolean processMenuEvent(@Nonnull final PlatformMenuEvent event, @Nullable @MayContainNull final Object... objects) {
    final PlatformMenuAction action = this.actions.get(event);
    boolean handled = false;
    if (action != null) {
      handled = action.doPlatformMenuAction(event, objects);
    }
    return handled;
  }

  @Override
  public void handleAbout(@Nonnull final ApplicationEvent ae) {
    processMenuEvent(PlatformMenuEvent.ABOUT);
  }

  @Override
  public void handleOpenApplication(@Nonnull final ApplicationEvent ae) {
  }

  @Override
  public void handleOpenFile(@Nonnull final ApplicationEvent ae) {
    ae.setHandled(processMenuEvent(PlatformMenuEvent.OPEN_FILE, ae.getFilename()));
  }

  @Override
  public void handlePreferences(@Nonnull final ApplicationEvent ae) {
    ae.setHandled(processMenuEvent(PlatformMenuEvent.PREFERENCES));
  }

  @Override
  public void handlePrintFile(@Nonnull final ApplicationEvent ae) {
    ae.setHandled(processMenuEvent(PlatformMenuEvent.PRINT_FILE, ae.getFilename()));
  }

  @Override
  public void handleQuit(@Nonnull final ApplicationEvent ae) {
    ae.setHandled(processMenuEvent(PlatformMenuEvent.QUIT));
  }

  @Override
  public void handleReOpenApplication(@Nonnull final ApplicationEvent ae) {
    ae.setHandled(processMenuEvent(PlatformMenuEvent.REOPEN_APPLICATION));
  }

  @Override
  @Nonnull
  public String getName() {
    return "MAC OSX";
  }
}
