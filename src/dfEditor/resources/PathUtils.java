/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dfEditor.resources;

/**
 *
 * @author jtaylor
 */
public class PathUtils
{    
    public static String absolutePath(String file, String base)
    {   
        StringBuilder absolutePath = new StringBuilder();
        String[] absoluteDirectories= base.split("\\\\");
        String[] relativeDirectories= file.split("\\\\");
        int lastCommonRoot = 0;

        for(int i=0; i<relativeDirectories.length; ++i)
            if(relativeDirectories[i].equals(".."))
                lastCommonRoot++;
            else
                break;
        
        for(int i=0; i<absoluteDirectories.length-lastCommonRoot; ++i)
            if(absoluteDirectories[i].length() > 0)
                absolutePath.append(absoluteDirectories[i] + "\\\\");

        for(int i=lastCommonRoot; i<relativeDirectories.length-1; ++i)
            if(relativeDirectories[i].length() > 0)
                absolutePath.append(relativeDirectories[i] + "\\\\");

        absolutePath.append(relativeDirectories[relativeDirectories.length-1]);

        return absolutePath.toString();
    }


    public static String relativePath(String file, String base) throws Exception
    {       
        String[] absoluteDirectories = base.split("\\\\");
        String[] relativeDirectories = file.split("\\\\");
        int length = Math.min(absoluteDirectories.length, relativeDirectories.length);
        int lastCommonRoot = -1;
        
        for(int i = 0; i<length; ++i)
            if (absoluteDirectories[i].equals(relativeDirectories[i]))
                ++lastCommonRoot;
            else break;
        
        if (lastCommonRoot > -1)
        {
            lastCommonRoot += 1;
            StringBuilder relativePath = new StringBuilder();
            for(int i=lastCommonRoot; i<absoluteDirectories.length; ++i)
                if(absoluteDirectories[i].length() > 0)
                    relativePath.append("..\\");
            for(int i=lastCommonRoot; i<relativeDirectories.length-1; ++i)
                relativePath.append(relativeDirectories[i] + "\\");
            
            relativePath.append(relativeDirectories[relativeDirectories.length - 1]);
            
            return relativePath.toString();
        }
        else
            throw new Exception("No common root found between working direcotry and filename");   
    } 
}