/**
 * The Pentaho proprietary code is licensed under the terms and conditions
 * of the software license agreement entered into between the entity licensing
 * such code and Pentaho Corporation. 
 */
package org.pentaho.di.ui.repository.pur.repositoryexplorer.uisupport;

import org.pentaho.di.ui.repository.pur.repositoryexplorer.IUIEEUser;
import org.pentaho.di.ui.repository.pur.repositoryexplorer.controller.EESecurityController;
import org.pentaho.di.ui.repository.repositoryexplorer.uisupport.AbstractRepositoryExplorerUISupport;
import org.pentaho.di.ui.repository.repositoryexplorer.uisupport.RepositoryExplorerDefaultXulOverlay;

public class ManageRolesUISupport extends AbstractRepositoryExplorerUISupport implements java.io.Serializable {

  private static final long serialVersionUID = -9200289466156055097L; /* EESOURCE: UPDATE SERIALVERUID */

  @Override
  public void setup() {
    EESecurityController manageUserAndRolesController = new EESecurityController();
    handlers.add(manageUserAndRolesController);
    controllerNames.add(manageUserAndRolesController.getName());
    overlays.add(new RepositoryExplorerDefaultXulOverlay("org/pentaho/di/ui/repository/pur/repositoryexplorer/xul/security-with-role-enabled.xul", IUIEEUser.class)); //$NON-NLS-1$
  }
}
