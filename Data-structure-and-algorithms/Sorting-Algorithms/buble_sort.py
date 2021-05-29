import timeit
class bubble_sort:
    def __init__(self, A,n):
        self.A = A
        self.n = n

    def bubble_sort(self,A,n):
        for i in range(n):
            for j in range(0,n-i-1):
                if A[j] > A[j+1]:
                    A[j],A[j+1] = A[j+1],A[j]
    def show_arr(self):
        start_quick = timeit.default_timer()
        self.bubble_sort(self.A,self.n)
        stop_quick = timeit.default_timer()
        print('Time bubble sort comsume: ',stop_quick-start_quick)
        print(self.A[:10])
        return stop_quick-start_quick