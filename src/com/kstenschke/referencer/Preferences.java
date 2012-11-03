/*
 * Copyright 2012 Kay Stenschke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kstenschke.referencer;

import com.intellij.ide.util.PropertiesComponent;
import com.kstenschke.referencer.FileUtils;
import org.jetbrains.annotations.NonNls;

/**
 * Utility functions for preferences handling
 * All preferences of the Referencer plugin are stored on application level (not per project)
 */
public class Preferences {

		//  @NonNls = element is not a string requiring internationalization and it does not contain such strings.
	@NonNls
	private static final String PROPERTY_REFERENCER_INDEX = "PluginReferencer.SelectedIndex";

	@NonNls
	private static final String PROPERTY_REFERENCER_INDEX_PHP = "PluginReferencer.SelectedIndexPHP";

	@NonNls
	private static final String PROPERTY_REFERENCER_INDEX_JS = "PluginReferencer.SelectedIndexJS";



	/**
	 * Store referencer preferences: selected index per supported file type
	 *
	 * @param	fileExtension			File extension of file open while using referencer
	 * @param	selectedIndex			Selected item index
	 */
	public static void saveSelectedIndex(String fileExtension, Integer selectedIndex) {
		String preferenceIdentifier = getPropertyIdentifierByFileExtension(fileExtension);

		PropertiesComponent.getInstance().setValue(preferenceIdentifier, selectedIndex.toString());
	}



	/**
	 * Get identifier for referencer preference of given file extension
	 *
	 * @param	fileExtension		File extension of file open while using referencer
	 * @return						Preference identifier
	 */
	private static String getPropertyIdentifierByFileExtension(String fileExtension) {
		if( FileUtils.isPhpFileExtension(fileExtension) ) {
			return PROPERTY_REFERENCER_INDEX_PHP;
		} else if( FileUtils.isJavaScriptFileExtension(fileExtension) ) {
			return PROPERTY_REFERENCER_INDEX_JS;
		}

			// Default
		return PROPERTY_REFERENCER_INDEX;
	}



	/**
	 * @param	fileExtension	Extension of file open while invoking referencer
	 * @return					Integer
	 */
	public static Integer getSelectedIndex(String fileExtension) {
		String preferenceIdentifier	= getPropertyIdentifierByFileExtension(fileExtension);

		String preferredIndex	= PropertiesComponent.getInstance().getValue(preferenceIdentifier);
		if( preferredIndex == null || preferredIndex.equals("")  ) return 0;

		return Integer.parseInt(preferredIndex);
	}

}