package com.lynxspa.fpm.nodes.patch;


import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.nodes.OutputConnectable;

/**
 * A node that iterates on an array of objects and forward the contained objects one by one.<br>
 * In case the following execution throws an exception the current item is sent through the exception output
 * and if the exception is handled (i.e.: not thrown back) the node keeps iterating, execution stops otherwise.
 *
 * @author marta.veneziano
 *
 * @param <OUT> The type of the iterable element, hence the type of the output message.
 */
@NodeBeautifier(description = "Array Iteration Node", 
				smallIcon = "../icons/iteration_16.gif", largeIcon = "../icons/iteration_32.gif",
				category = "Information Flow")
public class PatchedArrayIteratorNode<OUT> extends BasicIteratorNode<OUT> implements OutputConnectable<OUT[]>
{
    private OutputConnectable< ? super OUT> nodeConnectedToOut_;

    public void connectNodeToOut(OutputConnectable< ? super OUT> node)
    {
        nodeConnectedToOut_ = node;
    }

    public OutputConnectable< ? super OUT> getNodeConnectedToOut()
    {
        return nodeConnectedToOut_;
    }

    public void process(OUT[] message) throws Exception
    {
    	terminated_ = false;
    	for(int ic1=0;ic1<message.length;ic1++)
        {
    		terminated_=(ic1>=message.length);
        		safeExecute(message[ic1]);
        }
    }
}
