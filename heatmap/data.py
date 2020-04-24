import csv

def getTestData():
    return [
    ("Bedroom",	-52,	-27,	-72),
    ("Bedroom",	-47,	-29,	-70),
    ("Bedroom",	-46,	-32,	-62),
    ("Bedroom",	-46,	-36,	-62),
    ("Bedroom",	-47,	-32,	-63),
    ("Bedroom",	-51,	-31,	-58),
    ("Bedroom",	-49,	-39,	-58),
    ("Bedroom",	-50,	-40,	-55),
    ("Living Room",	-38,	-47,	-52),
    ("Living Room",	-36,	-40,	-57),
    ("Living Room",	-44,	-36,	-56),
    ("Living Room",	-34,	-42,	-49),
    ("Living Room",	-51,	-47,	-47),
    ("Living Room",	-37,	-48,	-60),
    ("Living Room",	-30,	-38,	-56),
    ("Living Room",	-34,	-44,	-52),
    ("Living Room",	-36,	-54,	-43),
    ("Living Room",	-29,	-57,	-49),
    ("Living Room",	-36,	-46,	-45),
    ("Living Room",	-25,	-61,	-45),
    ("Living Room",	-47,	-46,	-43),
    ("Living Room",	-61,	-49,	-46),
    ("Living Room",	-43,	-42,	-40),
    ("Living Room",	-43,	-53,	-36),
    ("Balcony",	-36,	-42,	-55),
    ("Balcony",	-46,	-41,	-62),
    ("Balcony",	-40,	-58,	-55),
    ("Balcony",	-45,	-44,	-55),
    ("Balcony",	-37,	-50,	-53),
    ("Balcony",	-42,	-41,	-55),
    ("Balcony",	-40,	-52,	-47),
    ("Balcony",	-55,	-53,	-57),
    ("Kitchen",	-59,	-67,	-53),
    ("Kitchen",	-49,	-64,	-58),
    ("Kitchen",	-53,	-55,	-51),
    ("Kitchen",	-46,	-56,	-60),
    ("Front Door",	-55,	-64,	-63),
    ("Front Door",	-47,	-68,	-57),
    ("Front Door",	-65,	-58,	-73),
    ("Front Door",	-64,	-60,	-48),
    ("Bathroom",	-51,	-53,	-41),
    ("Bathroom",	-49,	-49,	-48),
    ("Bathroom",	-44,	-55,	-31),
    ("Bathroom",	-50,	-54,	-43),
    ("Bathroom",	-58,	-67,	-55),
    ("Bathroom",	-48,	-64,	-47),
    ("Bathroom",	-48,	-69,	-57),
    ("Bathroom",	-48,	-59,	-32),
    ]

def getTestDataAsPoints():
    return [
        ("Bedroom 1", -52, -27, -72),
        ("Bedroom 1", -47, -29, -70),
        ("Bedroom 1", -46, -32, -62),
        ("Bedroom 1", -46, -36, -62),
        ("Bedroom 2", -47, -32, -63),
        ("Bedroom 2", -51, -31, -58),
        ("Bedroom 2", -49, -39, -58),
        ("Bedroom 2", -50, -40, -55),
        ("Living Room 1", -38, -47, -52),
        ("Living Room 1", -36, -40, -57),
        ("Living Room 1", -44, -36, -56),
        ("Living Room 1", -34, -42, -49),
        ("Living Room 2", -51, -47, -47),
        ("Living Room 2", -37, -48, -60),
        ("Living Room 2", -30, -38, -56),
        ("Living Room 2", -34, -44, -52),
        ("Living Room 3", -36, -54, -43),
        ("Living Room 3", -29, -57, -49),
        ("Living Room 3", -36, -46, -45),
        ("Living Room 3", -25, -61, -45),
        ("Living Room 4", -47, -46, -43),
        ("Living Room 4", -61, -49, -46),
        ("Living Room 4", -43, -42, -40),
        ("Living Room 4", -43, -53, -36),
        ("Balcony 1", -36, -42, -55),
        ("Balcony 1", -46, -41, -62),
        ("Balcony 1", -40, -58, -55),
        ("Balcony 1", -45, -44, -55),
        ("Balcony 2", -37, -50, -53),
        ("Balcony 2", -42, -41, -55),
        ("Balcony 2", -40, -52, -47),
        ("Balcony 2", -55, -53, -57),
        ("Kitchen 1", -59, -67, -53),
        ("Kitchen 1", -49, -64, -58),
        ("Kitchen 1", -53, -55, -51),
        ("Kitchen 1", -46, -56, -60),
        ("Front Door 1", -55, -64, -63),
        ("Front Door 1", -47, -68, -57),
        ("Front Door 1", -65, -58, -73),
        ("Front Door 1", -64, -60, -48),
        ("Bathroom 1", -51, -53, -41),
        ("Bathroom 1", -49, -49, -48),
        ("Bathroom 1", -44, -55, -31),
        ("Bathroom 1", -50, -54, -43),
        ("Bathroom 2", -58, -67, -55),
        ("Bathroom 2", -48, -64, -47),
        ("Bathroom 2", -48, -69, -57),
        ("Bathroom 2", -48, -59, -32),
    ]



def getAccesspoints():
    return ["LivingRoomAP","BedroomAP","BathroomAP"]
def getRooms():
    return [
    "Bedroom",
    "Kitchen",
    "Living Room",
    "Balcony",
    "Bathroom",
    "Front Door"
    ]

def getRoomLocations():
    return [
    "Bedroom 1",
    "Bedroom 2",
    "Kitchen 1",
    "Living Room 1",
    "Living Room 2",
    "Living Room 3",
    "Living Room 4",
    "Balcony 1",
    "Balcony 2",
    "Bathroom 1",
    "Bathroom 2",
    "Front Door 1"
    ]



def getData():
    csvfile = open('data.csv') #, newline=''
    dataReader = csv.reader(csvfile)

    data = []
    for row in dataReader:
        # Timestamp,Location,Direction,LivingRoomAP,BedroomAP,BathroomAP
        room = row[1]
        ap1 = int(row[3])
        ap2 = int(row[4])
        ap3 = int(row[5])
        data.append([room,ap1,ap2,ap3])

    return data

def getDataRooms():
    data = getData()
    for o in data:
        strings = o[0].split(' ')
        del strings[len(strings)-1]

        roomName = strings[0]
        for i in range (1, len(strings)):
            roomName = roomName + " " +strings[i]

        o[0] = roomName

    return data