items = int(input())
inputData = list(map(int, input().split()))
list = sorted([(inputData[i], inputData[i+1]) for i in range(0, 2*items-1, 2)],key=lambda x: x[0]+x[1])
day = 0
maximum = 0
for building in list:
    if day+building[0] <= building[1]:
        maximum += 1
        day += building[0]
print(maximum)
