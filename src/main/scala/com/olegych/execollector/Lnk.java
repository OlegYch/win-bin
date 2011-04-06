package com.olegych.execollector;

import com.roxes.win32.LnkFile;

/**
 * @author OlegYch
 */
public class Lnk extends LnkFile {

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Lnk");
    sb.append("{arguments='").append(getArguments()).append('\'');
    sb.append(", description='").append(getDescription()).append('\'');
    sb.append(", folder='").append(getFolder()).append('\'');
    sb.append(", iconIndex=").append(getIconIndex());
    sb.append(", iconLocation='").append(getIconLocation()).append('\'');
    sb.append(", linkFilePath='").append(getLinkFilePath()).append('\'');
    sb.append(", name='").append(getName()).append('\'');
    sb.append(", path='").append(getPath()).append('\'');
    sb.append(", workingDirectory='").append(getWorkingDirectory()).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
