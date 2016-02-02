package com.igormaznitsa.ideamindmap.print;

import com.igormaznitsa.ideamindmap.utils.AllIcons;
import com.igormaznitsa.ideamindmap.utils.IdeaUtils;
import com.igormaznitsa.mindmap.model.logger.Logger;
import com.igormaznitsa.mindmap.model.logger.LoggerFactory;
import com.igormaznitsa.mindmap.print.MMDPrintPanel;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Window;

public class IdeaMMDPrintPanelAdaptor implements MMDPrintPanel.Adaptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(IdeaMMDPrintPanelAdaptor.class);

  private final Project project;

  public IdeaMMDPrintPanelAdaptor(@NotNull final Project project){
    this.project = project;
  }

  @Override public void startBackgroundTask(@NotNull final MMDPrintPanel source,@NotNull final String taskName, @NotNull final Runnable task) {
    final Task.Backgroundable backgroundTask= new Task.Backgroundable(this.project, taskName) {
      @Override public void run(@NotNull final ProgressIndicator indicator) {
        try{
          indicator.setIndeterminate(true);
          task.run();
          IdeaUtils.showPopup(String.format("%s has been sent to the printer",taskName), MessageType.INFO);
        }catch(Exception ex){
          LOGGER.error("Print error",ex);
          IdeaUtils.showPopup("Print error! See the log!", MessageType.ERROR);
        }finally{
          indicator.stop();
        }
      }
    };
    ProgressManager.getInstance().run(backgroundTask);
  }

  @Override public boolean isDarkTheme(final MMDPrintPanel source) {
    return IdeaUtils.isDarkTheme();
  }

  @Override public Icon getIcon(final MMDPrintPanel source, final MMDPrintPanel.IconId iconId) {
    switch (iconId) {
    case PAGE:
      return AllIcons.PopUp.PAGE;
    case PRINTER:
      return AllIcons.PopUp.PRINTER;
    default:
      return null;
    }
  }

  @Override public void onPrintTaskStarted(final MMDPrintPanel source) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override public void run() {
        final Window wnd = SwingUtilities.windowForComponent(source);
        if (wnd!=null) wnd.dispose();
      }
    });
  }

  @Override public Dimension getPreferredSizeOfPanel(final MMDPrintPanel mmdPrintPanel) {
    return new Dimension(600, 450);
  }

}