import queue
from Node import Node 
from Util import *

class HuffmanTree:
    def __init__(self):
        self.root = None
        self.table = {}

    # Costructs the Huffman tree on the input text
    def build(self, text):
        # STEP 1: Count frequencies of characters in source text (runtime = O(n))
        freqs = frequencies(text)

        # STEP 2: Build min priority queue of nodes for each char (O(n))
        pq = queue.PriorityQueue()
        for c in freqs.keys():
            n = Node(c, freqs[c]) # create node for this char with its frequency
            pq.put(n) 

        # STEP 3: Build the tree (O(n))
        while pq.qsize() > 1:
            left = pq.get() # pop two smallest frequency nodes from pq
            right = pq.get()
            
            # create new internal node whose frequency is sum of its children's frequencies
            node = Node('$', left.frequency + right.frequency) # '$' denotes an internal node (a node that has no char)
            node.left = left
            node.right = right
            pq.put(node)
        
        self.root = pq.get()

    # DFS the tree, building a dict mapping chars to their encodings
    def getCodes(self):
        self.getCodesAux(self.root, "")
        return self.table

    def getCodesAux(self, node: Node, code: str):
        if node.character != '$': # node is leaf
            self.table[node.character] = code
        else:
            self.getCodesAux(node.left, code + "0") # left children add a 0 
            self.getCodesAux(node.right, code + "1") # right children add a 1

    def __repr__(self):
        return str(self.root)