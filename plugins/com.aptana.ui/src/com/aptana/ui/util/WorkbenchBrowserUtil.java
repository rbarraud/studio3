/**
 * Aptana Studio
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */

package com.aptana.ui.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

import com.aptana.ui.UIPlugin;

/**
 * @author Max Stepanov
 *
 */
public final class WorkbenchBrowserUtil {

	/**
	 * 
	 */
	private WorkbenchBrowserUtil() {
	}

	public static void launchExternalBrowser(String url) {
		try {
			launchExternalBrowser(new URL(url));
		} catch (MalformedURLException e) {
			UIPlugin.log(e);
		}
	}

	public static void launchExternalBrowser(URL url) {
		launchExternalBrowser(url, null);
	}

	public static IWebBrowser launchExternalBrowser(String url, String browserId) {
		try {
			return launchExternalBrowser(new URL(url), browserId);
		} catch (MalformedURLException e) {
			UIPlugin.log(e);
		}
		return null;
	}

	public static IWebBrowser launchExternalBrowser(URL url, String browserId) {
		IWorkbenchBrowserSupport support = PlatformUI.getWorkbench().getBrowserSupport();
		if (browserId != null) {
			try {
				IWebBrowser webBrowser = support.createBrowser(IWorkbenchBrowserSupport.AS_EXTERNAL, browserId, null, null);
				if (webBrowser != null) {
					webBrowser.openURL(url);
					return webBrowser;
				}
			} catch (PartInitException e) {
				UIPlugin.log(e);
			}
		}
		try {
			IWebBrowser webBrowser = support.getExternalBrowser();
			webBrowser.openURL(url);
			return webBrowser;
		} catch (PartInitException e) {
			UIPlugin.log(e);
		}
		return null;
	}
}
