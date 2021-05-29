import timeit
class insertion_sort:
    def __init__(self, A,n):
        self.A = A
        self.n = n

    def insertion_sort(self,A,n):
        if n<=1:
            return
        self.insertion_sort(A,n-1)
        last = A[n-1]
        j = n-2
        while(j>=0 and A[j] > last):
            A[j+1] =A[j]
            j=j-1
        A[j+1] = last
    def show_arr(self):
        start_quick = timeit.default_timer()
        self.insertion_sort(self.A,self.n)
        stop_quick = timeit.default_timer()
        print('Time bubble sort comsume: ',stop_quick-start_quick)
        print(self.A[:10])
        return stop_quick-start_quick