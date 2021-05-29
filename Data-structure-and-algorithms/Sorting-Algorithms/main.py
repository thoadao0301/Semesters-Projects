import numpy as np
from numpy.random import sample
import quick_sort
import selection_sort
import buble_sort
import insertion_sort
import dual_pivot_quicksort
import mergesort
import matplotlib.pyplot as plt

example_size = [10,100,500]
per_sample_size = []

for i in example_size:
    time_of_each_sample = {}
    A = np.random.randint(10000,size=i)
    n = len(A)
    print('Array size: ', i)
    print(A[:10])
    print('############')
    print('Sorting by insertion_sort with size: ', i)
    A_sort_ins = insertion_sort.insertion_sort(A,n)
    time = A_sort_ins.show_arr()
    time_of_each_sample['insertion-sort'] = time*1000


    print('############')
    print('Sorting by quick_sort with size: ', i)
    A_sort = quick_sort.quick_sort(A,0,n-1)
    time = A_sort.show_arr()
    time_of_each_sample['quick-sort'] = time*1000


    print('############')
    print('Sorting by selection_sort with size: ', i)
    A_sort_sel = selection_sort.selection_sort(A,n)
    time = A_sort_sel.show_arr()
    time_of_each_sample['selection-sort'] = time*1000


    print('############')
    print('Sorting by bubble_sort with size: ', i)
    A_sort_bub = buble_sort.bubble_sort(A,n)
    time = A_sort_bub.show_arr()
    time_of_each_sample['buble-sort'] = time*1000


    print('############')
    print('Sorting by dual_pivote_quick_sort with size: ', i)
    A_sort_dq = dual_pivot_quicksort.dual_p_quick_sort(A,0,n-1)
    time = A_sort_dq.show_arr()
    time_of_each_sample['dual-pivotes-quick-sort'] = time*1000


    print('############')
    print('Sorting by merge_sort with size: ', i)
    A_sort_mer = mergesort.merge_sort(A,0,n-1)
    time = A_sort_mer.show_arr()
    time_of_each_sample['merge-sort'] = time*1000
    print('')

    per_sample_size.append(time_of_each_sample)


N = 6
ind = np.arange(6)
width = 0.25

xvals = per_sample_size[0].values()
bar1 = plt.bar(ind, xvals, width, color = 'r')
  
yvals = per_sample_size[1].values()
bar2 = plt.bar(ind+width, yvals, width, color='g')
  
zvals = per_sample_size[2].values()
bar3 = plt.bar(ind+width*2, zvals, width, color = 'b')
  
plt.xlabel("Sorting Algorithms")
plt.ylabel('Time in milleseconds')
plt.title("Compare sort time of sorting algorithms")
  
plt.xticks(ind+width,per_sample_size[0].keys())
plt.legend( (bar1, bar2, bar3), ('10','100','500') )
plt.show()



