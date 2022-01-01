def createPreOrder(inOrderTraversal, postOrderTraversal, stack):
    if(len(inOrderTraversal) <= 1):
        if(len(inOrderTraversal) == 1):
            stack.append(inOrderTraversal[0])
        return
    value = postOrderTraversal[-1]
    stack.append(value)
    inOrderMiddleIndex = len(inOrderTraversal) - \
        (inOrderTraversal[::-1].index(value)+1)
    leftInorder = inOrderTraversal[0:inOrderMiddleIndex]
    rightInorder = inOrderTraversal[inOrderMiddleIndex+1:]
    leftPostOrder = postOrderTraversal[0:len(leftInorder)]
    rightPostOrder = postOrderTraversal[len(leftInorder):-1]
    createPreOrder(leftInorder, leftPostOrder, stack)
    createPreOrder(rightInorder, rightPostOrder, stack)


def printPreOrder(inOrderTraversal, postOrderTraversal):
    stack = []
    createPreOrder(inOrderTraversal, postOrderTraversal, stack)
    print(*stack, sep=" ")


input()
inorder = list(map(int, input().split()))
postorder = list(map(int, input().split()))
printPreOrder(inorder, postorder)
