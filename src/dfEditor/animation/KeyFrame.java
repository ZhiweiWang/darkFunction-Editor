package dfEditor.animation;

/**
 *
 * @author jtaylor
 */
public class KeyFrame
{
    public String name;
    public float time;

    public KeyFrame copy()
    {
        KeyFrame frame = new KeyFrame();
        frame.name = name;
        frame.time = time;

        return frame;
    }
}
