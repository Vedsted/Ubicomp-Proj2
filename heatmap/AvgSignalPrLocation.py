import numpy as np
import matplotlib
import matplotlib.pyplot as plt
from data import getData, getRoomLocations, getAccesspoints
from heatmap import heatmap, annotate_heatmap
# sphinx_gallery_thumbnail_number = 2

Accesspoints = getAccesspoints()
Rooms = getRoomLocations()
Data = getData()

def getAvg(obs):
    # Returns avg ap level for a given room
    a1 = 0
    a2 = 0
    a3 = 0

    for o in obs:
        a1 = a1 + o[1]
        a2 = a2 + o[2]
        a3 = a3 + o[3]

    return [a1 / len(obs), a2 / len(obs), a3 / len(obs)]


matrix = []
for room in Rooms:
    observations = []
    for d in Data:
        if d[0] == room:
            observations.append(d)

    matrix.append(getAvg(observations))

measures = np.array(matrix)

fig, ax = plt.subplots(figsize=(5, 8))

im, cbar = heatmap(measures, Rooms, Accesspoints, ax=ax,
                   cmap="Blues", cbarlabel="Signal strength in dBm ")
texts = annotate_heatmap(im, valfmt="{x:.1f} ")

fig.tight_layout()
plt.savefig("Plots/AvgSignalPrLocation.svg", bbox_inches = "tight") # 'tight' makes room for x-axis labels
plt.show()