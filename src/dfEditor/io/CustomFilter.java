/* 
 *  Copyright 2012 Samuel Taylor
 * 
 *  This file is part of darkFunction Editor
 *
 *  darkFunction Editor is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  darkFunction Editor is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with darkFunction Editor.  If not, see <http://www.gnu.org/licenses/>.
 */

package dfEditor.io;

import java.util.ArrayList;
import java.io.File;
import javax.swing.filechooser.*;


public class CustomFilter extends FileFilter
{
    public enum eExtension
    {
        Sprites,
        Anims,
        Gifs
    }
    
    public final static String EXT_ANIM = "anim";
    public final static String EXT_SPRITE = "sprites";
    public final static String EXT_GIF = "gif";

    private ArrayList<eExtension> ext = null;

    public CustomFilter()
    {
        ext = new ArrayList<eExtension>();
    }

    public String getExtension()
    {
        return getExtensionStr(ext.get(0));
    }
    
    public String getExtensionStr(eExtension eExt)
    {
        switch(eExt)
        {
            case Sprites:
                return EXT_SPRITE;
            case Anims:
                return EXT_ANIM;
            case Gifs:
                return EXT_GIF;
        }
        
        return "";
    }

    public void addExtension(eExtension eExt)
    {
        ext.add(eExt);
    }

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f);
        if (extension != null) 
        {
            for (int i=0; i<ext.size(); ++i)
            {
                if (extension.equals(getExtensionStr(ext.get(i))))
                    return true;
            }            
        }

        return false;
    }

    public String getDescription() 
    {
        String desc = new String();
        for (int i=0; i<ext.size(); ++i)
        {
            String verbose = null;
            eExtension eExt = ext.get(i);
            
            switch(eExt)
            {
                case Sprites:
                    verbose = "Spritesheets";
                    break;
                case Anims:
                    verbose = "Animations";
                    break;
                case Gifs:
                    verbose = "Gif animated image";
                    break;
            }                

            desc += verbose + " ";
            if (verbose != null)
                desc += "(*."+getExtensionStr(eExt)+")";
            if (i < ext.size()-1)
                desc += ", ";
        }
        return desc;
    }
}

