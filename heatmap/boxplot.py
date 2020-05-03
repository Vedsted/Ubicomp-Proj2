import data
import matplotlib.pyplot as plt
import numpy as np
from matplotlib.patches import Polygon

# AP1: Living Room, AP2: Bedroom, AP3: Bathroom
# Room, ap1, ap2, ap3
dataPoints = data.getDataRooms()

for room in data.getRooms():

    ap1 = []
    ap2 = []
    ap3 = []

    for v in dataPoints:
        if v[0] == room:
            ap1.append(v[1])
            ap2.append(v[2])
            ap3.append(v[3])

    fig, ax = plt.subplots(figsize=(5, 8))

    ax.boxplot([ap1, ap2, ap3], 0, '')
    ax.set_title(room + " Signal Strength", fontsize=14)
    ax.set_xlabel(str(len(ap1)) + ' observations for each AP', fontsize=14)
    ax.set_ylabel('dBm', fontsize=14)
    ax.set_xticklabels(["AP1: Living Room", "AP2: Bedroom", "AP3: Bathroom"], fontsize=11)

    fig.tight_layout()
    plt.savefig("Plots/boxplot_" + room.replace(" ", "") + ".svg", bbox_inches = "tight") # 'tight' makes room for x-axis labels
    plt.show()