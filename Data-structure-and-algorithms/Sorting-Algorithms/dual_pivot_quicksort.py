import timeit
class dual_p_quick_sort:
    def __init__(self, A,first,last):
        self.A = A
        self.first = first
        self.last = last

    def dual_p_quick_sort(self,A,first,last):
        if(first >= last):
            return 
        lower = first
        upper = last
        pivot1 = A[lower]
        pivot2 =  A[upper]
        if  pivot1>pivot2:
            A[lower],A[upper] = A[upper],A[lower]
        elif pivot1 == pivot2:
            while(pivot1 == pivot2 and upper <lower):
                lower+=1
                pivot1=A[lower]
        i = lower+1
        lt = lower + 1
        gt = upper -1
        while(i<=gt):
            if (A[i] < pivot1):
                A[i],A[lt] = A[lt] = A[i]
                i+=1
                lt+=1
            elif pivot2 < A[i]:
                A[i],A[gt] = A[gt],A[i]
                gt-=1
            else:
                i+=1
        lt-=1
        gt+=1
        A[lower],A[lt] = A[lt],A[lower]
        A[upper],A[gt] = A[gt],A[upper]
        self.dual_p_quick_sort(A,first,lt-1)
        self.dual_p_quick_sort(A,lt+1,lt-1)
        self.dual_p_quick_sort(A,gt+1,last)
    def show_arr(self):
        start_quick = timeit.default_timer()
        self.dual_p_quick_sort(self.A,self.first,self.last)
        stop_quick = timeit.default_timer()
        print('Time quick sort consume: ',stop_quick-start_quick)
        print(self.A[:10])
        return stop_quick-start_quick
