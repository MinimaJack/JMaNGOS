/*******************************************************************************
 * Copyright (c) 2012 JMANGOS
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     JMANGOS - initial API and implementation
 ******************************************************************************/
package org.jmangos.commons.configuration.transformers;

import java.io.File;
import java.lang.reflect.Field;

import org.jmangos.commons.configuration.PropertyTransformer;
import org.jmangos.commons.configuration.TransformationException;


public class FileTransformer implements PropertyTransformer<File>
{
	/**
	 * Shared instance of this transformer. It's thread-safe so no need of multiple instances
	 */
	public static final FileTransformer	SHARED_INSTANCE	= new FileTransformer();

	/**
	 * Transforms String to the file.
	 *
	 * @param value value that will be transformed
	 * @param field value will be assigned to this field
	 * @return File object that represents string
	 * @throws TransformationException the transformation exception
	 */
	@Override
	public File transform(String value, Field field) throws TransformationException
	{
		return new File(value);
	}
}