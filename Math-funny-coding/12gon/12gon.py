import numpy as np
import matplotlib.pyplot as plt
import math

a = [[0.2679,0],[0,0.2679]]
e=[[1/2,math.sqrt(3)/2],[math.sqrt(3)/2,1/2],[0, 1],[math.sqrt(3)/2,-1/2],
    [1/2,-(math.sqrt(3))/2],[-1,0],[ -(math.sqrt(3))/2,-1/2],
    [-1/2,-(math.sqrt(3))/2],[0,-1],[-(math.sqrt(3))/2,1/2],[-1/2,math.sqrt(3)/2],[1,0]]


I = np.round(np.random.rand(5000000)*11).astype('int')

point = []
tmp = []
input_x = [0,0]
input_flag = True
for i in range(len(I)):
    if input_flag:
        tmp = np.dot(a,input_x)+ e[I[i]]
        input_flag = False
        point.append(tmp)
    else:
        tmp = np.dot(a,tmp)+ e[I[i]]
        point.append(tmp)

arr = np.asarray(point)
plt.figure(figsize = (100,100))
plt.scatter(arr[1:,0],arr[1:,1])