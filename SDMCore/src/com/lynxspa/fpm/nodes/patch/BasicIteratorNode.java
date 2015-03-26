package com.lynxspa.fpm.nodes.patch;

import java.util.Iterator;

import com.lynxit.fpm.nodes.ExceptionNonTerminalNode;
import com.lynxit.fpm.nodes.ExceptionOutputConnectable;
import com.lynxit.fpm.nodes.NonTerminalNode;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.NodeSupport;

/**
 * A node that iterates on a {@link java.lang.Iterator} object and forwards the items one by one.<br>
 * In case the following execution throws an exception the current item is sent through the exception output
 * and if the exception is handled (i.e.: not thrown back) the node keeps iterating, execution stops otherwise.
 *
 * @author luca.zenti
 *
 * @param <OUT> The type of the iterable element, hence the type of the output message.
 */
public abstract class BasicIteratorNode<OUT> extends NodeSupport implements ExceptionNonTerminalNode<OUT>, NonTerminalNode
{
    private ExceptionOutputConnectable< ? super OUT> exceptionNode_;

    protected boolean terminated_ = false;

    public void connectNodeToException(ExceptionOutputConnectable<? super OUT> node)
    {
        exceptionNode_ = node;
    }

    public ExceptionOutputConnectable< ? super OUT> getNodeConnectedToException()
    {
        return exceptionNode_;
    }

    public abstract OutputConnectable< ? super OUT> getNodeConnectedToOut();

    public void process(Iterator<OUT> message) throws Exception
    {
    	OUT item = null;
    	terminated_ = false;
        while (message.hasNext())
        {
            item = message.next();
            terminated_ = !message.hasNext();
            safeExecute(item);
        }
    }
    
    protected void safeExecute(OUT out) throws Exception
	{
		try
		{
		    getNodeConnectedToOut().process(out);
		}
		catch(Exception exc)
		{
		    // forward on exception node, if this node handles the exception fork cycle won't stop.
		    getNodeConnectedToException().processException(exc, out);
		}
	}

	public boolean isTerminated()
	{
		return terminated_;
	}

}
