class TreeNode:
    def __init__(self,key,color):
        self.key = key
        self.left = None
        self.right = None
        self.P = None
        self.color = color

class Redblack_Tree:
    def __init__(self):
        self.NIL = TreeNode(0,'B')
        self.root = self.NIL
        self.NIL.left = None
        self.NIL.right = None

    def printAlign(self,node,indent,state):
        if node != self.NIL:
            for i in range(indent):
                print(" ",end=" ")
            if state == 'root':
                print('Root-',end=' ')
            elif state == 'left':
                print('L-',end=' ')
            else:
                print('R-',end=' ')
            print(str(node.key) + "/" + str(node.color))
            self.printAlign(node.left, indent+3,'left')
            self.printAlign(node.right, indent+3,'right')


    def prettyPrint(self):
        self.printAlign(self.root,0,'root')


    def search(self,node,x):
        while(node!=self.NIL):
            if(x == node.key):
                return node
            elif (x <= node.key):
                node = node.left
            else:
                node = node.right
        return self.NIL
    
    def Leftrotate(self,ex_parent):
        ex_right_child = ex_parent.right
        ex_parent.right = ex_right_child.left
        if ex_right_child.left != self.NIL:
            ex_right_child.left.P = ex_parent
        ex_right_child.P = ex_parent.P
        if ex_parent.P == None:
            self.root = ex_right_child
        elif ex_parent == ex_parent.P.left:
            ex_parent.P.left = ex_right_child
        else:
            ex_parent.P.right = ex_right_child
        ex_right_child.left = ex_parent
        ex_parent.P = ex_right_child

    def Rightrotate(self,ex_parent):
        ex_left_child = ex_parent.left          # set variable ex left child
        ex_parent.left = ex_left_child.right    # move the right child of ex left child into the left child of ex parent
        if ex_left_child.right != self.NIL:      # check the right child of ex left child is NIL
            ex_left_child.right.P = ex_parent  # update his parent
        ex_left_child.P = ex_parent.P  # update parent of ex left child
        if ex_parent.P == None: # check root
            self.root = ex_left_child
        elif ex_parent == ex_parent.P.left:  # if parent' Child is left child or right child
            ex_parent.P.left = ex_left_child
        else:
            ex_parent.P.right = ex_left_child
        ex_left_child.right = ex_parent  # update right child of ex left child
        ex_parent.P = ex_left_child   # update parent of ex-parent
        

    def add(self,x):
        node = TreeNode(x,'R')
        node.key =x
        node.left = self.NIL
        node.right = self.NIL
        if self.root == self.NIL:
            self.root = node
        else:
            pAfter = None
            pBefore = self.root
            while (pBefore != self.NIL and pBefore.key != node.key):
                pAfter = pBefore
                if node.key < pBefore.key:
                    pBefore = pBefore.left
                else:
                    pBefore = pBefore.right

            if pBefore != self.NIL:
                return self.NIL
            if node.key < pAfter.key:
                pAfter.left = node
            else:
                pAfter.right = node
            node.P = pAfter
        return node

    def check_color_tree(self,x):
        node = self.add(x)
        if node == self.NIL:
            return False
        else:
            if node.P == None:
                node.color = 'B'
                return True
            elif node.P.P == None:
                return True
            while(node != self.root and node.P.color == 'R'):
                if node.P == node.P.P.left:
                    old_uncle = node.P.P.right
                    if old_uncle.color == 'R' :
                        node.P.color = 'B'
                        old_uncle.color = 'B'
                        node.P.P.color = 'R'
                        node = node.P.P
                    else:
                        if(node == node.P.right): # case 2
                            node = node.P
                            self.Leftrotate(node) # backbone sub-tree when node in a right of parent
                        node.P.color = 'B'
                        node.P.P.color = 'R'
                        self.Rightrotate(node.P.P)
    
                else:
                    y_uncle = node.P.P.left
                    if y_uncle.color == 'R' :
                        node.P.color = 'B'
                        y_uncle.color = 'B'
                        node.P.P.color = 'R'
                        node = node.P.P
                    else:
                        if(node == node.P.left):
                            node = node.P
                            self.Rightrotate(node)
                        node.P.color = 'B'
                        node.P.P.color = 'R'
                        self.Leftrotate(node.P.P)
                if node==self.root:
                    break
        self.root.color = 'B'
        return True

    def transplant(self,u,v):
        if u.P == None:
            self.root = v
        elif u.P.left == u:
            u.P.left = v
        else:
            u.P.right = v
        v.P = u.P
    def minimum(self,node):
        while node.left != self.NIL:
            node = node.left
        return node
    def removeNode(self,key):
        node = self.search(self.root,key)
        if node == self.NIL:
            print('Could not to find')
            return self.NIL
        cp_node = node
        change_color_node = cp_node.color
        if node.left == self.NIL:
            x = node.right
            self.transplant(node,node.right)
        elif node.right == self.NIL:
            x = node.left
            self.transplant(node,node.left)
        else:
            cp_node = self.minimum(node.right)
            change_color_node = cp_node.color
            x = cp_node.right
            if cp_node.P == node:
                x.P = cp_node
            else:
                self.transplant(cp_node,cp_node.right)
                cp_node.right = node.right
                cp_node.right.P = cp_node
            
            self.transplant(node,cp_node)
            cp_node.left = node.left
            cp_node.left.P = cp_node
            cp_node.color = node.color
        if change_color_node == 'B':
            return x
    
    def check_color_tree_delete(self,key):
        node = self.removeNode(key)
        if node != self.NIL and node != None:
            while(node != self.root and node.color == 'B'):
                if node == node.P.left:
                    old_uncle = node.P.right
                    if old_uncle.color == 'R':
                        old_uncle.color == 'B'
                        node.P.color = 'R'
                        self.Leftrotate(node.P)
                        old_uncle = node.P.right
                    if old_uncle.left.color == 'B' and old_uncle.right.color =='B':
                        old_uncle.color = 'R'
                        node = node.P
                    else:
                        if old_uncle.right.color == 'B':
                            old_uncle.left.color = 'B'
                            old_uncle.color = 'R'
                            self.Rightrotate(old_uncle)
                            old_uncle = node.P.right
                        old_uncle.color = node.P.color
                        node.P.color = 'B'
                        old_uncle.right.color = 'B'
                        self.Leftrotate(node.P)
                        node = self.root
                else:
                    y_uncle = node.P.left
                    if y_uncle.color == 'R':
                        y_uncle.color == 'B'
                        node.P.color = 'R'
                        self.Rightrotate(node.P)
                        y_uncle = node.P.left
                    if y_uncle.left.color == 'B' and y_uncle.right.color =='B':
                        y_uncle.color = 'R'
                        node = node.P
                    else:
                        if y_uncle.left.color == 'B':
                            y_uncle.right.color = 'B'
                            y_uncle.color = 'R'
                            self.Leftrotate(y_uncle)
                            y_uncle = node.P.left
                        y_uncle.color = node.P.color
                        node.P.color = 'B'
                        y_uncle.left.color = 'B'
                        self.Rightrotate(node.P)
                        node = self.root
            node.color = 'B'


    def insert_tree(self):
        flag = True
        while(flag):
            x = input('Enter a number (S to stop): ')
            if x == 'S':
                break
            try:
                x = int(x)
            except:
                print('You need enter a number')
                continue
            if self.check_color_tree(x):
                print('Add successfully')
            else:
                print('Fail to add')
    def delete_node(self):
        x = input('Enter a number: ')
        try:
            x = int(x)
        except:
            print('You need enter a number')
            pass
        if self.check_color_tree_delete(x):
            print('Delete successfully')
        

if __name__ == '__main__':
    RBTree = Redblack_Tree()
    choice = {1 : 'Add Tree', 2: 'Print Tree', 3: 'Delete node', 4: 'Quit'}
    while(True):
        print(choice)
        x = input('Enter a number: ')
        try:
            x = int(x)
        except:
            print('You need enter a number')
            continue
        if x == 1:
            print(choice[1])
            RBTree.insert_tree()
            continue
        elif x==2:
            print(choice[2])
            RBTree.prettyPrint()
            continue
        elif x==3:
            print(choice[3])
            RBTree.delete_node()
            continue
        else:
            print(choice[4])
            break          

        
                    






            
        


