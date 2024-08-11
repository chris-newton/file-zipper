class Node:
    def __init__(self, character, frequency):
        self.character = character
        self.frequency = frequency
        self.left = None
        self.right = None

    def __eq__(self, other):
        return self.frequency == other.frequency
    
    def __lt__(self, other):
        return self.frequency < other.frequency
    
    def __repr__(self, level=0):
        indent = "    " * level
        f = f"{indent}(\"{self.character}\" | {self.frequency})\n"
        if self.left:
            f += f"{self.left.__repr__(level+1)}"
            f += f"{self.right.__repr__(level+1)}"  
        return f