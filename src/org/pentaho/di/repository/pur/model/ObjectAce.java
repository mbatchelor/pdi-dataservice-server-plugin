/**
 * The Pentaho proprietary code is licensed under the terms and conditions
 * of the software license agreement entered into between the entity licensing
 * such code and Pentaho Corporation. 
 */
package org.pentaho.di.repository.pur.model;

import java.util.EnumSet;

import org.pentaho.di.repository.ObjectRecipient;
import org.pentaho.platform.api.repository2.unified.RepositoryFilePermission;

public interface ObjectAce {

    public ObjectRecipient getRecipient();
    public EnumSet<RepositoryFilePermission> getPermissions();
  	public void setRecipient(ObjectRecipient recipient);
  	public void setPermissions(RepositoryFilePermission first, RepositoryFilePermission... rest);
  	public void setPermissions(EnumSet<RepositoryFilePermission> permissions);
}
