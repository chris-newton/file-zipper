# returns a dict of chars and their frequency in the text
def frequencies(text: str):
    f = {}
    for c in text:
        if c in f:
            f[c] += 1
        else:
            f[c] = 1
    return f