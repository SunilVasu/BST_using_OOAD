
//*************** Client Program *************

class BST {
	public static void main(String args[]) {
		 Tree tr;
		 tr = new Tree(100);
		 tr.insert(50);		 
		 tr.insert(125);
		 tr.insert(150);		 
		 tr.insert(25);
		 tr.insert(75);
		 tr.insert(20);	
		 tr.insert(90);		 
		 tr.delete(20);
		 tr.delete(20);
		 tr.delete(125);
		 tr.delete(150);
		 tr.delete(100);
		 tr.delete(50);
		 tr.delete(75);
		 tr.delete(25);
		 tr.delete(90);
		 
		 
		
	}
}

class Tree { // Defines one node of a binary search tree
	
	public Tree(int n) {
		value = n;
		left = null;
		right = null;
	}
	
	public void insert(int n) {
		if (value == n)
			return;
		if (value < n)
			if (right == null)
				right = new Tree(n);
			else
				right.insert(n);
		else if (left == null)
			left = new Tree(n);
		else
			left.insert(n);
	}
	public Tree max()
{
	if(right == null)
		return this;
	else
		return right.max();
}
	public Tree min()
{
	if(left == null)
		return this;
	else
		return left.min();
}
	public Tree find(int n)
	{
		if(value == n)
			return this;
		
		else if (value < n)
		{
			if(right == null)
				return null;
			else
				return right.find(n);
		}
		else
		{
			if(left == null)
				return null;
			else
				return left.find(n);
		}
	}
	public Tree findParent(int n)
	{
		if(value == n)					//this will happen only when the value is at root node
			return this;
		
		else if (value < n)				//it will return the parent node
		{
			if(right == null)
				return null;
			else if (right.value == n)
				return this;
			else
				return right.findParent(n);
		}
		else
		{
			if(left == null)
				return null;
			else if (left.value == n)
				return this;
			else
				return left.findParent(n);
		}
	}
	public void delete(int n) {  
		Tree par = this.findParent(n);
		if(par == null)					//no parent means -> node not found
			System.out.println("Unable to delete "+n+" -- not in the tree!");
		else
		{
			Tree delNode;
			if(par.value==n)		//this happens only for root node
			{
				//code for root node
				if(right == null && left == null)			//only root is left
					System.out.println("Unable to delete "+value+" -- tree will be empty!");
				else
				{
					//while deleting root node, replace it with max at left nodes
					Tree maxAtLeft;
					if(left == null)		//if no left node, then jut make the right one root
					{
						this.value = right.value;
						this.left = right.left;
						this.right = right.right;
					}
					else
					{
						maxAtLeft = left.max();
						delete(maxAtLeft.value);
						this.value = maxAtLeft.value;
						
					}
				}
				return;
			}
			else
			{
				if(par.value < n)
					delNode = par.right;
				else
					delNode = par.left;
				if(delNode.left == null && delNode.right == null)
				{
					//call case1 - leaf node
					par.delete_case1(n);
				}
				else if(delNode.left != null && delNode.right != null)
				{
					//call case 3 
					par.delete_case3(n);
				}
				else
				{
					par.delete_case2(n);
				}
			}
				
		}
	

     }
	public void delete_case1( int n)
	{
		//leaf node
		if(value>n)			//value to be deleted is at left of parent, make it null
		{
			left = null;
		}
		else				//value to be deleted is at right of parent, make it null
		{
			right = null;
		}
	}
	
	public void delete_case2(int n)
	{
		if(value>n)			//value to be deleted is at left of parent. so copy the non-null node from left
		{
			left = left.left == null? left.right:left.left;
		}
		else				//value to be deleted is at right of parent. so copy the non-null node from right
		{
			right = right.left == null? right.right:right.left;
		}
	}
	
	public void delete_case3(int n)
	{
		Tree minAtRight;
		if(value>n)			//value to be deleted is at left of parent. 
		{
			minAtRight = left.right.min();
			left.delete(minAtRight.value);
			minAtRight.left = left.left;
			minAtRight.right = left.right;
			left = minAtRight;
		}
		else				//value to be deleted is at left of parent. 
		{
			minAtRight = right.right.min();
			right.delete(minAtRight.value);
			minAtRight.left = right.left;
			minAtRight.right = right.right;
			right = minAtRight;
		}
		
	}
	
	protected int value;
	protected Tree left;
	protected Tree right;
}

























