/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import Classes.ClassForClass;
import umlclassdiagrams.UMLClassDrawer;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 * @since 3.3
 */
public class GlobalMenuHandler extends AbstractHandler implements IWorkbenchWindowActionDelegate {
	/**
	 * The constructor.
	 */
	getJavaFiles getfiles;
	List<String> getJavaFilesPath;
	ParsingClassFiles pcf;
	UMLClassDrawer classDraw;
	List<ClassForClass> classes = new ArrayList<>();
	public GlobalMenuHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event){
		
		return null;
	}

	@Override
	public void run(IAction arg0) {
		// TODO Auto-generated method stub
		try {
			classDraw = new UMLClassDrawer();
			//classDraw.kill();
			new ParsingClassFiles();
			getJavaFilesPath = new ArrayList<String>();//list that takes java files
			getfiles = new getJavaFiles();//get file object
			File setStart = new File("E:\\Google Drives\\3ο Εξάμηνο\\Java3");
			JFileChooser filechooser = new JFileChooser(setStart);
			filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			filechooser.showOpenDialog(filechooser);
			String path = filechooser.getSelectedFile().getPath();//path given by user
			getfiles.getAllJavaFiles(path,getJavaFilesPath);//finds class files an returns into a list
			for(String string:getJavaFilesPath) {
				
				if(!(string.equals(null))) {
					
					pcf = new ParsingClassFiles();	
					classes.add(pcf.ParsingListwithClassFiles(string));
				}
			}
			classDraw.buildTable(classes);
			System.exit(0);
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getCause().toString());
			System.exit(0);
		}
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow arg0) {
		// TODO Auto-generated method stub
		
	}
}
