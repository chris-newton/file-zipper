import os
from HuffmanTree import HuffmanTree

def main():
    cwd = os.getcwd()  # Get the current working directory (cwd)
    files = os.listdir(cwd)  # Get all the files in that directory
    print("Files in %r: %s" % (cwd, files))
    
    # read text file
    with open('./data/lorem.txt') as f:
        text = f.read()

    print(f"Input text: {text}\n")
    
    # Build the tree
    huffTree = HuffmanTree()
    huffTree.build(text)
    # print(huffTree)

    # Calculate the codes
    codes = huffTree.getCodes()
    print(f"Codes: {codes}\n")

    # Calculate the encoding of the input text
    encoding = ""
    for c in text:
        encoding += codes[c]

    print(f'Encoded text: {encoding}\n')

    # Decode the text
    node = huffTree.root
    decodedText = ""

    for b in encoding:
        if b == "0":
            node = node.left
        else:
            node = node.right
        if node.character != '$':
            decodedText += node.character
            node = huffTree.root
        
    print(f"Decoded text: {decodedText}\n")

    print(f'Input text size: {len(text) * 8}') # Assuming text is in UTF-8, each char is 8 bits
    print(f'Compressed text size: {len(encoding)}')

if __name__ == "__main__": 
    main()