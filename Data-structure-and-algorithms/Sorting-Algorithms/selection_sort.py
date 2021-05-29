import timeit

class selection_sort:
    def __init__(self,A,n):
        self.A = A
        self.n = n
        
    def minIndex(self,A,i,j):
        if i == j:
            return i
    
        k=self.minIndex(A,i+1,j)

        return (i if A[i] <A[k] else k)

    def recur_selection_sort(self,A,n, index=0):
        if index == n:
            return -1
    
        k = self.minIndex(A,index,n-1)

        if k != index:
            A[k],A[index] = A[index],A[k]

        self.recur_selection_sort(A,n,index+1)
    def show_arr(self):
        start_quick = timeit.default_timer()
        self.recur_selection_sort(self.A,self.n)
        stop_quick = timeit.default_timer()
        print('Time selection sort comsume: ',stop_quick-start_quick)
        print(self.A[:10])
        return stop_quick-start_quick

