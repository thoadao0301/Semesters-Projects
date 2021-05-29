import timeit
import numpy as np
class merge_sort:
    def __init__(self,A,first,last):
        self.A = A
        self.first =first
        self.last = last
    def merge(self,A,first,last):
        tmp = np.zeros(len(A))
        mid = (first+last)//2
        i = first
        j = mid+1
        k=0
        while(i<=mid and j<=last):
            if A[i] < A[j]:
                tmp[k] = A[i]
                k+=1
                i+=1
            else:
                tmp[k] = A[j]
                k+=1
                j+=1
        while i<mid:
            tmp[k] = A[i]
            k+=1
            i+=1
        while j<last:
            tmp[k] = A[j]
            k+=1
            j+=1
        A = tmp
    def merge_sort(self,A,first,last):
        if first==last:
            return
        mid = (first+last)//2
        if(first<mid):
           self.merge_sort(A,first,mid)
        if(mid+1<last):
           self.merge_sort(A,mid+1,last)
        self.merge(A,first,last)
    def show_arr(self):
        start_quick = timeit.default_timer()
        self.merge_sort(self.A,self.first,self.last)
        stop_quick = timeit.default_timer()
        print('Time quick sort consume: ',stop_quick-start_quick)
        print(self.A[:10])
        return stop_quick-start_quick