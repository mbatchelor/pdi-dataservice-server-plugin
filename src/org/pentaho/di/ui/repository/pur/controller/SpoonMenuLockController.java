/**
 * The Pentaho proprietary code is licensed under the terms and conditions
 * of the software license agreement entered into between the entity licensing
 * such code and Pentaho Corporation. 
 */
package org.pentaho.di.ui.repository.pur.controller;

import org.pentaho.di.core.EngineMetaInterface;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.pur.PurRepository;
import org.pentaho.di.repository.pur.model.EEJobMeta;
import org.pentaho.di.repository.pur.model.ILockable;
import org.pentaho.di.repository.pur.model.RepositoryLock;
import org.pentaho.di.ui.repository.pur.services.ILockService;
import org.pentaho.di.ui.spoon.ISpoonMenuController;
import org.pentaho.di.ui.spoon.Spoon;
import org.pentaho.ui.xul.components.XulMenuitem;
import org.pentaho.ui.xul.components.XulToolbarbutton;
import org.pentaho.ui.xul.dom.Document;

public class SpoonMenuLockController implements ISpoonMenuController, java.io.Serializable {

  private static final long serialVersionUID = -1007051375792274091L; /* EESOURCE: UPDATE SERIALVERUID */

  public String getName() {
    return "spoonMenuLockController"; //$NON-NLS-1$
  }

  public void updateMenu(Document doc) {
    try {
      Spoon spoon = Spoon.getInstance();

      // If we are working with an Enterprise Repository
      if ((spoon != null) && (spoon.getRepository() != null) && (spoon.getRepository() instanceof PurRepository)) {
        ILockService service = getService(spoon.getRepository());

        EngineMetaInterface meta = spoon.getActiveMeta();

        // If (meta is not null) and (meta is either a Transformation or Job)
        if ((meta != null) && (meta instanceof ILockable)) {

          RepositoryLock repoLock = null;
          if (service != null && meta.getObjectId() != null) {
            if (meta instanceof EEJobMeta) {
              repoLock = service.getJobLock(meta.getObjectId());
            } else {
              repoLock = service.getTransformationLock(meta.getObjectId());
            }
          }
          // If (there is a lock on this item) and (the UserInfo does not have permission to unlock this file)
          if (repoLock != null) {
            if (!service.canUnlockFileById(meta.getObjectId())) {
              // User does not have modify permissions on this file
              ((XulToolbarbutton) doc.getElementById("toolbar-file-save")).setDisabled(true); //$NON-NLS-1$
              ((XulMenuitem) doc.getElementById("file-save")).setDisabled(true); //$NON-NLS-1$  
            }
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private ILockService getService(Repository repository) throws KettleException {
    if (repository.hasService(ILockService.class)) {
      return (ILockService) repository.getService(ILockService.class);
    } else {
      throw new IllegalStateException();
    }
  }
}
