import timeit
class quick_sort:
    def __init__(self, A,first,last):
        self.A = A
        self.first = first
        self.last = last

    def median(self,a,b,c):
        if((a>b and a<c) or (a>c and a<b)):
            return a
        if (b>a and b<c) or (b>c and b<a):
            return b
        return c
    def quick_sort(self,A,first,last):
        if(first >= last):
            return 
        mid=(first+last)//2
        pivot = self.median(A[first],A[last],A[mid])
        lower=first
        upper=last
        while(lower<=upper):
            while(lower<=upper and pivot > A[lower]):
                lower+=1
            while(lower<=upper and pivot <A[upper]):
                upper-=1
            if lower<upper:
                A[lower],A[upper] = A[upper],A[lower]
                upper-=1
                lower+=1
            else:
                lower+=1
        self.quick_sort(A,first,upper)
        self.quick_sort(A,upper+1,last)
    def show_arr(self):
        start_quick = timeit.default_timer()
        self.quick_sort(self.A,self.first,self.last)
        stop_quick = timeit.default_timer()
        print('Time quick sort comsume: ',stop_quick-start_quick)
        print(self.A[:10])
        return stop_quick-start_quick

